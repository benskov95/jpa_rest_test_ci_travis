
//const one = document.getElementById("sdiv1");
//const two = document.getElementById("sdiv2");
//
//function hello(div) {
//    div.addEventListener("click", function(){console.log("Hi from " + this.id);}, false);
//}
//
//hello(one);
//hello(two);

const paragraph = document.getElementById("p1");

function helloFromTarget() {
//    console.log("Hello from TARGET: " + event.target.id);
return "Hello from TARGET: " + event.target.id;
}

function helloFromThis() {
//    console.log("Hello from THIS: " + this.id);
    var helloTarget = helloFromTarget();
    var helloThis = "Hello from THIS: " + this.id;
    
   paragraph.innerHTML = helloTarget + "<br>" + helloThis;
}

document.getElementById("outer").addEventListener("click", helloFromThis, false);