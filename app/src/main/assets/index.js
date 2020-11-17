//import init, {isLimitReached} from './lib.js';
import './lib.js';

var isWasmAvailable = false;

async function run() {
	await init()
	isWasmAvailable = true;
}

function hello(){
    console.log("Hello");
}

run()

console.log("inside index.js")
console.log(isLimitReached)
//export {isLimitReached, hello}


