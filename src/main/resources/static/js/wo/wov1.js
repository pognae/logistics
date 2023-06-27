 

const createScene = function() {
    const scene = new BABYLON.Scene(engine);
    scene.clearColor = new BABYLON.Color3.Black; //공간 색

    //카메라 설정
    setCamera(scene);

    //조명설정
    const light = setLight(); 

    // ****************************
    // 테스트 17820개 뿌리기 
    // ****************************
    let box = null;
    let cnt = 0;
    for (let k=0; k <6; k++) {
        for (let j= -25; j <25; j++){
            for (let i = -50; i  <= 50; i++) {
                
                if( ( (i%10 != 0) && ( j%3 !=0) )   ){
                    //박스설정
                    box = setBox(scene, "boxname" + i, i, k, j);
                    //box 클릭 이벤트
                    setBoxEvent(scene, box);
                    cnt++;
                }
            }
        }
    }
    console.log("cnt="+ cnt);
    
    // ****************************
    // 테스트 기준좌표 찍기 
    // ****************************
    //X=50
    box = setBox(scene, "boxX", 50, 0, 0);
    setBoxEvent(scene, box);
    //z=25
    box = setBox(scene, "boxZplus", 0, 0, 25);
    setBoxEvent(scene, box);
    //z=-25
    box = setBox(scene, "boxZminus", 0, 0, -25);
    setBoxEvent(scene, box);


    // ****************************
    // rack 설정
    // ****************************
    //setRack(scene, "rack1", {type: "rack1", x: 0, y: 0, z: 0, width: 10, height: 5, depth: 1, section: 20, floor: 6});
    //setRack(scene, "rack1", {type: "rack2", x: 0, y: 0, z: 2, width: 10, height: 5, depth: 1, section: 20, floor: 6});
 


    //바닥 설정
    ground = setFloor(scene, 100,50);


    const xrPromise = scene.createDefaultXRExperienceAsync({
        floorMeshes: [ground]
    });

    return xrPromise.then((xrExperience) => {
        console.log("Done, WebXR is enabled.");
        return scene;
    });
};



function setCamera(scene) {
    //카메라 설정
    //const alpha =  Math.PI/4; //좌<->우 회전            
    const alpha =  Math.PI/-4; //좌<->우 회전
    const beta = Math.PI/3; //상<->하 회전
    
    
    const radius = 100; //카메라 거리는 바닥의W,H 제일 긴 넓이많큼
    const target = new BABYLON.Vector3(0, 0, 0);

    const camera = new BABYLON.ArcRotateCamera("Camera", alpha, beta, radius, target, scene); //카메라
    camera.attachControl(canvas, true);  
}
function setLight(){
    //조명설정
    var light = new BABYLON.HemisphericLight("light", new BABYLON.Vector3(1, 1, 0)); //조명
    return light;
}

function setRack(scene, name, props){
    let rackX = 0;
    let rackY = 0;
    let rackZ = 0;
    let rackW = 10; //넓이(m)
    let rackH = 5; //높이(m)
    let rackD = 1; //깊이(m)
    let rackS = 20; //섹션 cnt
    let rackF = 4; //단 갯수 

    const rackFrame = [
    //1단
     [new BABYLON.Vector3(props.x, props.y, props.z), new BABYLON.Vector3(props.x+props.width , props.y, props.z) ] //1단 가로바 
    ,[new BABYLON.Vector3(props.x, props.y, props.z), new BABYLON.Vector3(props.x, props.y, props.z+props.depth) ] //1단 깊이바  
    ,[new BABYLON.Vector3(props.x, props.y, props.z+props.depth), new BABYLON.Vector3(props.x+props.width, props.y, props.z+props.depth) ] //1단 가로바 
    ,[new BABYLON.Vector3(props.x+props.width, props.y, props.z+props.depth), new BABYLON.Vector3(props.x+props.width, props.y, props.z) ] //1단 깊이바 

    //마지막 단
    ,[new BABYLON.Vector3(props.x, props.y+props.height, props.z), new BABYLON.Vector3(props.x+props.width , props.y+props.height, props.z) ] //가로바 
    ,[new BABYLON.Vector3(props.x, props.y+props.height, props.z), new BABYLON.Vector3(props.x, props.y+props.height, props.z+props.depth) ] //깊이바  
    ,[new BABYLON.Vector3(props.x, props.y+props.height, props.z+props.depth), new BABYLON.Vector3(props.x+props.width, props.y+props.height, props.z+props.depth) ] //가로바 
    ,[new BABYLON.Vector3(props.x+props.width, props.y+props.height, props.z+props.depth), new BABYLON.Vector3(props.x+props.width, props.y+props.height, props.z) ] //깊이바

    //세로바
    ,[new BABYLON.Vector3(props.x, props.y, props.z), new BABYLON.Vector3(props.x, props.y+props.height, props.z) ] //1번 
    ,[new BABYLON.Vector3(props.x, props.y, props.z+props.depth), new BABYLON.Vector3(props.x, props.y+props.height, props.z+props.depth) ] //2번
    ,[new BABYLON.Vector3(props.x+props.width, props.y, props.z+props.depth), new BABYLON.Vector3(props.x+props.width, props.y+props.height, props.z+props.depth) ] //3번
    ,[new BABYLON.Vector3(props.x+props.width, props.y, props.z), new BABYLON.Vector3(props.x+props.width, props.y+props.height, props.z) ] //4번

    ];            
    //Create linesystem
    const linesystem = BABYLON.MeshBuilder.CreateLineSystem("linesystem", {lines: rackFrame}); 


    let w = props.width/props.section;
    let h = props.height/props.floor;
    let d = props.depth; 
    let x = -(w/2);
    let y = -1;
    let z = +(d/2)
    for(let s=0;s<props.section;s++) {
        x=x+w;
        for(let f=0;f<props.floor;f++) {
            y=y+h;    
            //박스설정
            box = setBox3(scene, name, x, y, z, {width:w,height:h,depth:d});
            //box 클릭 이벤트
            //setBoxEvent(scene, box);                    
        }
        y=-1; 
    } 

}

//박스설정
//alpha에 따라 x  z 반전된다.
function setBox(scene, name, x, y, z) {
    let h = 1;
    //박스설정
    var box = BABYLON.MeshBuilder.CreateBox(name, { width: 0.9, height: h, depth:0.9}); //cubue
    box.position.x = x; //alpha(+) = (+)왼쪽   (-)오른쪽
    box.position.y = y + (h/2); //alpha(+) = (+)공중   (-)지하
    box.position.z = z; //alpha(+) = (+)밑으로 (-)위로

    var boxMaterial = new BABYLON.StandardMaterial("material", scene);
    boxMaterial.diffuseColor = BABYLON.Color3.Random();
    box.material = boxMaterial;

    return box;
}
function setBox2(scene, name, x, y, z, w, h, d) {
    //박스설정
    var box = BABYLON.MeshBuilder.CreateBox(name, { width: w, height: h, depth: d}); //cubue
    box.position.x = x; //alpha(+) = (+)왼쪽   (-)오른쪽
    box.position.y = y + (h/2); //alpha(+) = (+)공중   (-)지하
    box.position.z = z; //alpha(+) = (+)밑으로 (-)위로

    var boxMaterial = new BABYLON.StandardMaterial("material", scene);
    boxMaterial.diffuseColor = BABYLON.Color3.Random();
    box.material = boxMaterial;

    return box;
}        
function setBox3(scene, name, x, y, z, size) {
    let h = 1;
    let wgap = 0.1; //섹션gap
    let hgap = 0.1; //단gap
    //박스설정
    var box = BABYLON.MeshBuilder.CreateBox(name, { width: size.width-wgap, height: size.height-hgap, depth: size.depth}); //cubue
    box.position.x = x; //alpha(+) = (+)왼쪽   (-)오른쪽
    box.position.y = y + (h/2); //alpha(+) = (+)공중   (-)지하
    box.position.z = z; //alpha(+) = (+)밑으로 (-)위로

    var boxMaterial = new BABYLON.StandardMaterial("material", scene);
    boxMaterial.diffuseColor = BABYLON.Color3.Random();
    box.material = boxMaterial;

    return box;
}        
function setBoxEvent(scene, box){
    //box 클릭 이벤트 
    box.actionManager = new BABYLON.ActionManager(scene);
    box.actionManager.registerAction(
        new BABYLON.ExecuteCodeAction(BABYLON.ActionManager.OnPickTrigger, 
        function (evt) {
            const sourceBox = evt.meshUnderPointer;
            sourceBox.position.x += 0.1;
            sourceBox.position.y += 0.1; 

            //boxMaterial.diffuseColor = BABYLON.Color3.Random();
            box.material.diffuseColor = BABYLON.Color3.Random();
            console.log("click box name=" + box.name 
            + " x="+ sourceBox.position.x 
            + " y=" + sourceBox.position.y
            + " z=" + sourceBox.position.z
            );
    }));
}
function setFloor(scene, w,h){
    //바닥 설정
    var ground = BABYLON.MeshBuilder.CreateGround("ground", {width: 100, height: 50}); // 바닥
    ground.material = new BABYLON.GridMaterial("groundMaterial", scene);     

    return ground;
}  