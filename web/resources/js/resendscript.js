function handleAjax(data) {
    var status = data.status;

    switch(status) {
        case "begin":
            // This is invoked right before ajax request is sent.
            break;

        case "complete":
            // This is invoked right after ajax response is returned.
            break;

        case "success":
            // This is invoked right after successful processing of ajax response and update of HTML DOM.
            resendRequest();
            break;
    }
}
        
function resendRequest() 
{
    document.getElementById("jsfappform:reloadbut").click();
} 

function handleError(data)  { }
