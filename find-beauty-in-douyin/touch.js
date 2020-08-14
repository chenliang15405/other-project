var process = require('child_process');


function exec(shell) {
    process.exec(shell,function (error, stdout, stderr) {
        if (error !== null) {
          console.log('exec error: ' + error);
        }
    });
}


function click() {
    console.log('click')
    exec(`adb shell input tap 400 800`)
    // setTimeout(back, 1000)
}

function swipe() {
    console.log('swipe')
    exec(`adb shell input swipe 400 800 400 0 500`)
    setTimeout(click, 5000)
}

swipe()
