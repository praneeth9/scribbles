(function () {
    if (!console) {
        console = {};
    }
    var old = console.log;
    var logger = document.getElementById('log');
    console.log = function (message) {
        if (typeof message == 'object') {
            logger.innerHTML += (JSON && JSON.stringify ? JSON.stringify(message) : String(message)) + '<br />';
        } else {
            logger.innerHTML += message + '<br />';
        }
    }
})();
var title = function(msg){
   console.log("<h4 style='color:cornflowerblue;margin-bottom:-1%;border-bottom:1px dashed;'>"+msg+"</h4>")    
}
