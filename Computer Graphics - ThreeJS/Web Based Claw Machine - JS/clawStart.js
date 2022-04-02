////////////////////////////////////////////////////////////////////////////////

/* COMP 3490 A1 Skeleton for Claw Machine (Barebones Edition) 
 * Note that you may make use of the skeleton provided, or start from scratch.
 * The choice is up to you.
 * Read the assignment directions carefully
 * Your claw mechanism should be created such that it is represented hierarchically
 * You might consider looking at THREE.Group and THREE.Object3D for reference
 * If you want to play around with the canvas position, or other HTML/JS level constructs
 * you are welcome to do so.


 /*global variables, coordinates, clock etc.  */
var dummyCamera, camera, scene, renderer;
var spotLight1 , spotLight2;
var spotLightHelper1 , spotLightHelper2;
var cameraControls;
var egoView;
var incr = 1;
var goingDown , goingUp , stopped , closingClaw;

var clock = new THREE.Clock();


function fillScene() {

	scene = new THREE.Scene();


	scene.fog = new THREE.Fog( 0x808080, 2000, 4000 );

	// Some basic default lighting - in A2 complexity will be added

	scene.add( new THREE.AmbientLight( 0x222222 ) );

	var light = new THREE.DirectionalLight( 0xffffff, 0.7 );
	light.position.set( 200, 1000, 500 );

	

	spotLight1 = new THREE.SpotLight( 0xffffff, 45, 375, 0.65, 1, 1);
				spotLight1.position.set( -150, 770, -50 );
				spotLight1.rotation.set(0,0,0.15);

	spotLight2 = new THREE.SpotLight( 0xffffff, 45, 375, 0.65, 1, 1);
				spotLight2.position.set( 150, 770, -50 );
				spotLight2.rotation.set(0,0,-0.15);

	spotLight3 = new THREE.SpotLight( 0xffffff, 300, 375, 0.65, 1, 1);
				spotLight3.position.set( 150, 1200, -50 );
				spotLight3.rotation.set(0,0,-0.15);
			
	spotLightHelper1 = new THREE.SpotLightHelper( spotLight1 );
	scene.add( spotLightHelper1 );

   	spotLightHelper2 = new THREE.SpotLightHelper( spotLight2 );
	scene.add( spotLightHelper2 );

	spotLightHelper3 = new THREE.SpotLightHelper( spotLight3 );
	scene.add( spotLightHelper3 );



	scene.add( light );

	light = new THREE.DirectionalLight( 0xffffff, 0.9 );
	light.position.set( -200, -100, -400 );

	scene.add( light );
	scene.add(spotLight1);
	scene.add(spotLight2);





//A simple grid floor, the variables hint at the plane that this lies within
// Later on we might install new flooring.
 var gridXZ = new THREE.GridHelper(2000, 100, new THREE.Color(0xCCCCCC), new THREE.Color(0x888888));
 scene.add(gridXZ);

 //Visualize the Axes - Useful for debugging, can turn this off if desired
 var axes = new THREE.AxisHelper(150);
 axes.position.y = 1;
 scene.add(axes);

 drawClawMachine();
}




function drawClawMachine() {

	//////////////////////////////
	// Some simple material definitions - This may become more complex in A2

	//var textureMaterial = new THREE.MeshBasicMaterial({ map: THREE.ImageUtils.loadTexture('/images/wood-floor.jpg') });
	 var loader = new THREE.TextureLoader();
	  var textureTop = loader.load("./xmasTop.jpg");
	  var textureSides = loader.load("./xmasSides.jpg");
	  var textureunit = loader.load("./unitTexture.jpg");
	  var textureplastic = loader.load("./plastic.jpg");

	  var texturerope = loader.load("./rope.jpg");
	  texturerope.repeat.set(4, 4);
	  texturerope.wrapS = THREE.RepeatWrapping;
	  texturerope.wrapT = THREE.RepeatWrapping;

	  var textureconsole = loader.load("./console.jpg");
	

	var textureMaterial = new THREE.MeshLambertMaterial();
	textureMaterial.map = textureTop;

	var bodyMaterial = new THREE.MeshPhongMaterial();
	bodyMaterial.color.setRGB( 5, 5, 5 );
	bodyMaterial.map = textureSides;

	var unitMaterial = new THREE.MeshPhongMaterial();
	unitMaterial.map = textureunit;
	unitMaterial.shininess = 100;

	var plasticMaterial = new THREE.MeshPhongMaterial();
	plasticMaterial.color.setRGB(255,0,0);
	plasticMaterial.shininess = 100;

	var consoleMaterial = new THREE.MeshPhongMaterial();
	consoleMaterial.map = textureconsole;
	//consoleMaterial.color.setRGB(0,200,0);
	consoleMaterial.shininess = 50;


	var gaurdMaterial = new THREE.MeshLambertMaterial();
	gaurdMaterial.color.setRGB( 0.3, 0.6, 0.7 );
	var ropeMaterial = new THREE.MeshLambertMaterial();
	ropeMaterial.map = texturerope;

	var baseMaterial = new THREE.MeshLambertMaterial();
	baseMaterial.color.setRGB( 1, 1, 1 );

	var clawMaterial = new THREE.MeshLambertMaterial();
	clawMaterial.color.setRGB( 1, 0.1, 0.1 );

	var floorMaterial =  new THREE.MeshPhongMaterial();
	floorMaterial.color.setRGB(0.1,0.1,0.1);
	floorMaterial.shininess = 100;
	//floorMaterial.envMap = THREE.Reflection();
	floorMaterial.reflectivity = 100;

	var base;

	// This is where the model gets created. Add the appropriate geometry to create your machine
	// You are not limited to using BoxGeometry, and likely want to use other types of geometry for pieces of your submission
	// Note that the actual shape, size and other factors are up to you, provided constraints listed in the assignment description are met

floor = new THREE.Mesh(new THREE.BoxGeometry( 300, 400, 300 ), floorMaterial );
	floor.position.x = 0;
	floor.position.y = -150;
	floor.position.z = 0;
	scene.add( floor );

floorBase = new THREE.Mesh(new THREE.BoxGeometry( 3000, 40, 3000 ), baseMaterial );
	floorBase.position.x = 0;
	floorBase.position.y = -200;
	floorBase.position.z = 0;
	scene.add( floorBase );


 //The base
	base = new THREE.Mesh(new THREE.BoxGeometry( 300, 400, 300 ), bodyMaterial );
	base.position.x = 0;
	base.position.y = 200;
	base.position.z = 0;
	scene.add( base );
	
// 1st supporting arm
stand1 = new THREE.Mesh(new THREE.BoxGeometry( 50, 400, 50 ), bodyMaterial );
	stand1.position.x = -125;
	stand1.position.y = 600;
	stand1.position.z = -125;
	scene.add( stand1 );


// 2nd supporting arm
stand2 = new THREE.Mesh(
	new THREE.BoxGeometry( 50, 400, 50 ), bodyMaterial );
	stand2.position.x = 125;
	stand2.position.y = 600;
	stand2.position.z = -125;
	scene.add( stand2 );

// 3rd supporting arm
stand3 = new THREE.Mesh(
	new THREE.BoxGeometry( 50, 400, 50 ), bodyMaterial );
	stand3.position.x = 125;
	stand3.position.y = 600;
	stand3.position.z = 125;
	scene.add( stand3 );

// 4th supporting arm
stand4 = new THREE.Mesh(
	new THREE.BoxGeometry( 50, 400, 50 ), bodyMaterial );
	stand4.position.x = -125;
	stand4.position.y = 600;
	stand4.position.z = 125;
	scene.add( stand4 );

// cabinetTop
cabinetTop = new THREE.Mesh(
	new THREE.BoxGeometry( 400, 50, 400 ), textureMaterial );
	cabinetTop.position.x = 0;
	cabinetTop.position.y = 800;
	cabinetTop.position.z = 0;
	//cabinetTop.material.map = textureTop;
	scene.add( cabinetTop );

// consolePanel
consolePanel = new THREE.Mesh( 
		// radiusAtTop, radiusAtBottom, height, segmentsAroundRadius, segmentsAlongHeight,
		new THREE.CylinderGeometry( 30, 30, 80, 3, 4 ), 
		textureMaterial );
	consolePanel.position.set(0, 340, 160);
	consolePanel.rotation.set(0.15,0,1.57);
	consolePanel.scale.set(2,3.5,2);
	scene.add( consolePanel );


// joyShaft
joyShaft = new THREE.Mesh( 
		// radiusAtTop, radiusAtBottom, height, segmentsAroundRadius, segmentsAlongHeight,
		new THREE.CylinderGeometry( 5, 5, 120, 20, 4 ), 
		unitMaterial );
	joyShaft.position.set(-50, 360, 190);
	joyShaft.rotation.set(0.7, 0, 0);
	scene.add( joyShaft );


	// joySphere
joySphere = new THREE.Mesh( 
		new THREE.SphereGeometry( 20, 16, 8 ), 
		plasticMaterial );
	joySphere.position.set(-50, 415, 240);
	scene.add( joySphere );

	//button
button = new THREE.Mesh( 
		// radiusAtTop, radiusAtBottom, height, segmentsAroundRadius, segmentsAlongHeight,
		new THREE.CylinderGeometry( 20, 20, 10, 20, 4 ), 
		plasticMaterial );
	button.position.set(50, 360, 190);
	button.rotation.set(0.7, 0, 0);
	scene.add( button );

//Cutting out chute/bin in base

binOuter = new THREE.Mesh( new THREE.BoxGeometry( 150, 800, 150 ), consoleMaterial );
binOuter_bsp = new ThreeBSP( binOuter );

binInner =  new THREE.Mesh( new THREE.BoxGeometry( 120, 1000, 120 ) );
binInner.position.set(0,0,120);

binInner_bsp  = new ThreeBSP( binInner );
bin_bsp  = binOuter_bsp.subtract( binInner_bsp );

baseBSP = new ThreeBSP(base);
baseWithBin = baseBSP.subtract(binInner_bsp);

baseWithBin = baseWithBin.toMesh();
baseWithBin.position.set(0,200,0);
baseWithBin.material = consoleMaterial;

scene.remove(base);
scene.add(baseWithBin);

	//Gaurd

gaurdOuter = new THREE.Mesh( new THREE.BoxGeometry( 150, 800, 150 ), bodyMaterial );
gaurdOuter_bsp = new ThreeBSP( gaurdOuter );

gaurdInner =  new THREE.Mesh( new THREE.BoxGeometry( 120, 1000, 120 ) );
gaurdInner.position.set(0,0,0);

gaurdInner_bsp  = new ThreeBSP( gaurdInner );
gaurd_bsp  = gaurdOuter_bsp.subtract( gaurdInner_bsp );
gaurd = gaurd_bsp.toMesh();
gaurd.position.set(0,350,100);
gaurd.scale.set(0.8,0.3,0.6);
gaurd.material = gaurdMaterial;
scene.add(gaurd);

//prizePlatform

prizePlatform = new THREE.Mesh(
	new THREE.BoxGeometry( 150, 100, 100 ));
	prizePlatform.position.set(0,50,100); 
	scene.add( prizePlatform );


//framebar
framebar = new THREE.Mesh(
	new THREE.BoxGeometry( 10, 10, 220 ));
	framebar.position.set(0,750,0); 
	//scene.add( framebar );

//unit
unit = new THREE.Mesh(
	new THREE.BoxGeometry( 25, 30, 20 ));
	unit.position.set(0,745,0); 
	//scene.add( unit );

//clawString
clawString = new THREE.Mesh( 
		// radiusAtTop, radiusAtBottom, height, segmentsAroundRadius, segmentsAlongHeight,
		new THREE.CylinderGeometry( 0.5, 0.5, 300, 30, 40 ), 
		ropeMaterial );


	clawString.position.set(0,750,0);
	clawString.scale.set(1,0.1,1);
	clawString.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( 0, 100, 0 ) );
	clawString.rotation.set(0,0,3.14159);
	//scene.add( clawString );


claw = new THREE.Mesh( 
		new THREE.CylinderGeometry( 4, 8, 35, 30, 40 ),  
		clawMaterial );
	claw.position.set(0, 710, 0);
	scene.add( claw );

clawArm1Upper =new THREE.Mesh(
	new THREE.BoxGeometry( 30, 5, 5 ), bodyMaterial );
	clawArm1Upper.position.set(5,700,0); 
	clawArm1Upper.rotation.set(0,0,2.35619);
	clawArm1Upper.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -15, 0, 0 ) );
	//clawArm1Upper.rotation.set(0,0,1.57);
	//scene.add( clawArm1Upper );

clawArm1Lower =new THREE.Mesh(
	new THREE.BoxGeometry( 25, 5, 5 ), bodyMaterial );
	clawArm1Lower.position.set(25,682,0); 
	clawArm1Lower.rotation.set(0,0,0.785398);
	clawArm1Lower.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -12.5, 0, 0 ) );
	//scene.add( clawArm1Lower );


clawArm2Upper =new THREE.Mesh(
	new THREE.BoxGeometry( 30, 5, 5 ), bodyMaterial );
	clawArm2Upper.position.set(-5,700,0); 
	clawArm2Upper.rotation.set(0,3.14159,2.35619);
	clawArm2Upper.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -15, 0, 0 ) );
	//scene.add( clawArm2Upper );

clawArm2Lower =new THREE.Mesh(
	new THREE.BoxGeometry( 25, 5, 5 ), bodyMaterial );
	clawArm2Lower.position.set(-25,682,0); 
	clawArm2Lower.rotation.set(0,3.14159,0.785398);
	clawArm2Lower.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -12.5, 0, 0 ) );
	//scene.add( clawArm2Lower );

//clawArm2 = new THREE.Group;
//clawArm2.add(clawArm2Upper);
//clawArm2.add(clawArm2Lower);


clawArm3Upper =new THREE.Mesh(
	new THREE.BoxGeometry( 30, 5, 5 ), bodyMaterial );
	clawArm3Upper.position.set(0,700,-5); 
	clawArm3Upper.rotation.set(0,1.5708,2.35619);
	clawArm3Upper.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -15, 0, 0 ) );
	//scene.add( clawArm3Upper );

clawArm3Lower =new THREE.Mesh(
	new THREE.BoxGeometry( 25, 5, 5 ), bodyMaterial );
	clawArm3Lower.position.set(0,682,-25); 
	clawArm3Lower.rotation.set(0,1.5708,0.785398);
	clawArm3Lower.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -12.5, 0, 0 ) );
	//scene.add( clawArm3Lower );

//clawArm1 = new THREE.Group;
//clawArm1.add(clawArm1Upper);
//clawArm1.add(clawArm1Lower);


clawArm4Upper =new THREE.Mesh(
	new THREE.BoxGeometry( 30, 5, 5 ), bodyMaterial );
	clawArm4Upper.position.set(0,700,2.5); 
	clawArm4Upper.rotation.set(0,4.71239,2.35619);
	clawArm4Upper.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -15, 0, 0 ) );
	//scene.add( clawArm4Upper );

clawArm4Lower =new THREE.Mesh(
	new THREE.BoxGeometry( 25, 5, 5 ), bodyMaterial );
	clawArm4Lower.position.set(0,682,25); 
	clawArm4Lower.rotation.set(0,4.71239,0.785398);
	clawArm4Lower.geometry.applyMatrix( new THREE.Matrix4().makeTranslation( -12.5, 0, 0 ) );
	//scene.add( clawArm4Lower );

//clawArm2 = new THREE.Group;
//clawArm2.add(clawArm2Upper);
//clawArm2.add(clawArm2Lower);


clawArm1 = new THREE.Group;
clawArm1.add(clawArm1Upper);
clawArm1.add(clawArm1Lower);
//scene.add(clawArm1);

clawArm2 = new THREE.Group;
clawArm2.add(clawArm2Upper);
clawArm2.add(clawArm2Lower);
//scene.add(clawArm2);

clawArm3 = new THREE.Group;
clawArm3.add(clawArm3Upper);
clawArm3.add(clawArm3Lower);
//scene.add(clawArm3);

clawArm4 = new THREE.Group;
clawArm4.add(clawArm4Upper);
clawArm4.add(clawArm4Lower);
//scene.add(clawArm4);


allClaw = new THREE.Group;
allClaw.add(claw);
allClaw.add(clawArm1);
allClaw.add(clawArm2);
allClaw.add(clawArm3);
allClaw.add(clawArm4);


Xgroup = new THREE.Group();
Zgroup = new THREE.Group();


Zgroup.add(unit);
Zgroup.add(clawString);
Zgroup.add(allClaw);

Xgroup.add(framebar);
Xgroup.add(Zgroup);


scene.add(Xgroup);


var texture = new THREE.Texture(document.getElementById('texture'));
  texture.minFilter = THREE.LinearFilter;
  texture.needsUpdate = true;

glassMaterial = new THREE.MeshPhongMaterial({
    shininess: 100,
    color: 0xffffff,
    specular: 0xffffff,
    transparent: true,
    opacity: 0.6
  });

backGlass = new THREE.Mesh(new THREE.BoxGeometry( 300, 400, 5 ), glassMaterial );
	backGlass.position.x = 0;
	backGlass.position.y = 600;
	backGlass.position.z = -125;
	scene.add( backGlass );

frontGlass = new THREE.Mesh(new THREE.BoxGeometry( 300, 400, 5 ), glassMaterial );
	frontGlass.position.x = 0;
	frontGlass.position.y = 600;
	frontGlass.position.z = 145;
	scene.add( frontGlass );
rightGlass = new THREE.Mesh(new THREE.BoxGeometry( 280, 400, 5 ), glassMaterial );
	rightGlass.rotation.y = 1.57;
	rightGlass.position.x = 135;
	rightGlass.position.y = 600;
	rightGlass.position.z = -15;
	scene.add( rightGlass );

leftGlass = new THREE.Mesh(new THREE.BoxGeometry( 280, 400, 5 ), glassMaterial );
	leftGlass.rotation.y = 1.57;
	leftGlass.position.x = -135;
	leftGlass.position.y = 600;
	leftGlass.position.z = -15;
	scene.add( leftGlass );


toySphere1 = new THREE.Mesh( 
		new THREE.SphereGeometry( 20, 16, 8 ), 
		plasticMaterial );
	toySphere1.position.set(-50, 415, 0);
	scene.add( toySphere1 );

toySphere2 = new THREE.Mesh( 
		new THREE.SphereGeometry( 20, 16, 8 ), 
		plasticMaterial );
	toySphere2.position.set(10, 415, 0);
	scene.add( toySphere2 );

toySphere3 = new THREE.Mesh( 
		new THREE.SphereGeometry( 20, 16, 8 ), 
		plasticMaterial );
	toySphere3.position.set(-50, 415, -50);
	scene.add( toySphere3 );

toySphere4 = new THREE.Mesh( 
		new THREE.SphereGeometry( 20, 16, 8 ), 
		plasticMaterial );
	toySphere4.position.set(10, 415, -50);
	scene.add( toySphere4 );


}



// Initialization. Define the size of the canvas and store the aspect ratio
// You can change these as well

function init() {
	var canvasWidth = 1280;
	var canvasHeight = 720;
	var canvasRatio = canvasWidth / canvasHeight;

	// Set up a renderer. This will allow WebGL to make your scene appear
	renderer = new THREE.WebGLRenderer( { antialias: true } );

	renderer.gammaInput = true;
	renderer.gammaOutput = true;
	renderer.setSize(canvasWidth, canvasHeight);
	renderer.setClearColor( 0xAAAAAA, 1.0 );

	// You also want a camera. The camera has a default position, but you most likely want to change this.
	// You'll also want to allow a viewpoint that is reminiscent of using the machine as described in the pdf
	// This might include a different position and/or a different field of view etc.
	camera = new THREE.PerspectiveCamera( 45, canvasRatio, 1, 4000 );
	cubeCamera1 = new THREE.CubeCamera(1, 1000, 128);
	// Moving the camera with the mouse is simple enough - so this is provided. However, note that by default,
	// the keyboard moves the viewpoint as well


	cameraControls = new THREE.OrbitControls(camera, renderer.domElement);
	cameraControls.enablePan = false;
	camera.position.set( 0, 900, 1200);
	cameraControls.target.set(0,501,25);
	cameraControls.noPan = true;
	egoView = false;

}

	// We want our document object model (a javascript / HTML construct) to include our canvas
	// These allow for easy integration of webGL and HTML
function addToDOM() {
    var canvas = document.getElementById('canvas');
    canvas.appendChild(renderer.domElement);
}


	// This is a browser callback for repainting
	// Since you might change view, or move things
	// We cant to update what appears
	//(Note: Equavilent to Unity's Update() function)
function animate() {

	//document.write(clawArm1Lower.rotation.z);


	if(goingDown && !closingClaw)
	{
		clawString.scale.y += 0.006;
		allClaw.position.y -= 1.5;

		clawArm1Upper.rotation.z += 0.005;
		clawArm1Lower.rotation.z += 0.005;
		clawArm1Lower.position.x += 0.04;
		clawArm1Lower.position.y += 0.12155;


		clawArm2Upper.rotation.z += 0.005;
		clawArm2Lower.rotation.z += 0.005;
		clawArm2Lower.position.x -= 0.04;
		clawArm2Lower.position.y += 0.12155;


		clawArm3Upper.rotation.x += 0.005;
		clawArm3Lower.rotation.x += 0.005;
		clawArm3Lower.position.z -= 0.04;
		clawArm3Lower.position.y += 0.12155;


		clawArm4Upper.rotation.x -= 0.005;
		clawArm4Lower.rotation.x -= 0.005;
		clawArm4Lower.position.z += 0.04;
		clawArm4Lower.position.y += 0.12155;
	}

	if(clawString.scale.y >= 1.15)
	{
		//goingDown = false;
		closingClaw = true;
		goingUp = false;
	}

	if(goingDown && closingClaw && !goingUp)
	{

		if(clawArm1Lower.rotation.z <= 0.7853980 )
	{
		goingUp = true;
	}

		clawArm1Upper.rotation.z -= 0.005;
		clawArm1Lower.rotation.z -= 0.005;
		clawArm1Lower.position.x -= 0.04;
		clawArm1Lower.position.y -= 0.12155;


		clawArm2Upper.rotation.z -= 0.005;
		clawArm2Lower.rotation.z -= 0.005;
		clawArm2Lower.position.x += 0.04;
		clawArm2Lower.position.y -= 0.12155;


		clawArm3Upper.rotation.x -= 0.005;
		clawArm3Lower.rotation.x -= 0.005;
		clawArm3Lower.position.z += 0.04;
		clawArm3Lower.position.y -= 0.12155;


		clawArm4Upper.rotation.x += 0.005;
		clawArm4Lower.rotation.x += 0.005;
		clawArm4Lower.position.z -= 0.04;
		clawArm4Lower.position.y -= 0.12155;
	}

	
	

	if(goingUp && goingDown && !stopped)
	{
		clawString.scale.y -= 0.006;
		allClaw.position.y += 1.5;
	}

	if(goingDown && goingUp && clawString.scale.y <= 0.1)
	{
		stopped = true;
	}

	window.requestAnimationFrame(animate);
	if (window.isLeftArrowDown) 
	{
    	joyShaft.rotation.set(0.5,0,0.7);
    	joySphere.position.set(-90, 400, 210);
    	if(Xgroup.position.x >= -95){
    	Xgroup.position.x -= 0.5;
    	}
  	}
  	else if (window.isRightArrowDown) 
	{
    	joyShaft.rotation.set(0.5,0,-0.7);
    	joySphere.position.set(-10, 400, 210);
    	if(Xgroup.position.x <= 95){
    	Xgroup.position.x += 0.5;
    	}
  	}
  	else if (window.isDownArrowDown) 
	{
    	joyShaft.rotation.set(1.5,0,0);
    	joyShaft.position.set(-50, 360, 200);
    	joySphere.position.set(-50, 365, 250);

    	if(Zgroup.position.z <= 100){
    	Zgroup.position.z += 0.5;
    	}
  	}
  	else if (window.isUpArrowDown) 
	{
    	joyShaft.rotation.set(0,0,0);
    	joyShaft.position.set(-50, 370, 180);
    	joyShaft.scale.set(1,0.85,1);
    	joySphere.position.set(-50, 430, 180);
    	if(Zgroup.position.z >= -100){
    	Zgroup.position.z -= 0.5;
    	}
  	}
  	else if (window.isSpaceDown)
  	{
  		button.position.set(50, 350, 190);
	    button.rotation.set(0.7, 0, 0);
	    if(!goingDown)
	    {
	    goingDown = true;
	    closingClaw = false;
		}




  		/*if(clawString.scale.y >= 1.25)
  		{
  		goingDown = true;
  		}
  		else if (clawString.scale.y <= 0.1)
  		{
		goingDown = false;
  		}


  		if(goingDown)
  		{
  			//clawString.position.y -= 1;
  			claw.position.y += 1.5;
  			clawString.scale.y -= 0.006;
  			//incr += 0.0082;
  		}

  		if(!goingDown)
  		{
  			//clawString.position.y += 1;
  			claw.position.y -= 1.5;
  			clawString.scale.y += 0.006;
  			//incr -= 0.0082;
  		}*/

  	}
  	
  	else{
  		joyShaft.rotation.set(0.7, 0, 0);
  		joyShaft.position.set(-50, 360, 190);
  		joyShaft.scale.set(1,1,1);
  		joySphere.position.set(-50, 415, 240);
  		button.position.set(50, 360, 190);
	button.rotation.set(0.7, 0, 0);
  	}

  	if (window.isVDown && !egoView) 
	{
		setTimeout(function(){
		camera.position.set( 0,700, 200);
		cameraControls.target.set(0,400,-80);
		egoView = true;
		}, 150);
  	}
  	else if (window.isVDown && egoView) 
  	{
		setTimeout(function(){
  		camera.position.set( 0, 900, 1200);
		cameraControls.target.set(0,501,25);
		egoView = false;
		}, 150);
  	}



	render();
}


	// getDelta comes from THREE.js - this tells how much time passed since this was last called
	// This might be useful if time is needed to make things appear smooth, in any animation, or calculation
	// The following function stores this, and also renders the scene based on the defined scene and camera
function render() {
	var delta = clock.getDelta();
	cameraControls.update(delta);
	renderer.render(scene, camera);
	
}


function handleKeyDown(event) {
  if (event.keyCode === 37) { 
    window.isLeftArrowDown = true;
  }
  if (event.keyCode === 39) { 
    window.isRightArrowDown = true;
  }
  if (event.keyCode === 38) { 
    window.isUpArrowDown = true;
  }
  if (event.keyCode === 40) { 
    window.isDownArrowDown = true;
  }
  if (event.keyCode === 32) { 
    window.isSpaceDown = true;
  }
  if (event.keyCode === 86) { 
    window.isVDown = true;
  }
}

function handleKeyUp(event) {
  if (event.keyCode === 37) {
    window.isLeftArrowDown = false;
  }
  if (event.keyCode === 39) {
    window.isRightArrowDown = false;
  }
  if (event.keyCode === 38) {
    window.isUpArrowDown = false;
  }
  if (event.keyCode === 40) {
    window.isDownArrowDown = false;
  }
  if (event.keyCode === 32) {
    window.isSpaceDown = false;
  }
  if (event.keyCode === 86) {
    window.isVDown = false;
  }

}

window.addEventListener('keydown', handleKeyDown, false);
window.addEventListener('keyup', handleKeyUp, false);

	// Since we're such talented programmers, we include some exception handeling in case we break something
	// a try and catch accomplished this as it often does
	// The sequence below includes initialization, filling up the scene, adding this to the DOM, and animating (updating what appears)
try {
  init();
  fillScene();
  addToDOM();
  animate();
} catch(error) {
    console.log("You did something bordering on utter madness. Error was:");
    console.log(error);
}
