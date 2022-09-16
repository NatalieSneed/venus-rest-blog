import CreateView from "../createView.js";

let me;
export default function prepareUserHTML(props) {
    me = props.me;
    const userHTML = generateUserHTML(props.user);
    return `
            <h1>User Info</h1>
            <h2>${props.user.username}</h2>
            <h2>${props.me.email}</h2>

        <form>
        <label for="oldpassword">Please enter your current password</label>
        <input type="password" id="oldpassword" name="oldpassword" placeholder="Enter your current password" required>      
        
        <label for="newpassword">New password</label>
        <input type="password" id="newpassword" name="newpassword">
        <br>
        <label for="confirmpassword">Confirm new password</label>
        <input type="password" id="confirmpassword" name="confirmpassword">
        <br>
        <button id"toggleShowPassword" name="toggleShowPassword">Show Password</button>
        <button id="updatePassword" name="updatePassword">Save New Password</button>      
</form>   
 `;
}
export function prepareUserJS() {
    doTogglePasswordHandler();
    doSavePasswordHandler();
}

function doSavePasswordHandler() {
    const button = document.querySelector("#updatePassword");
    button.addEventListener("click", function (event) {
        //    grab the 3 password fields
        const oldPasswordField = document.querySelector('#oldpassword');
        const newPasswordField = document.querySelector('#newpassword');
        const confirmPasswordField = document.querySelector('#confirmpassword');

        const oldPassword = oldPassword.value;
        const newPassword = newPassword.value;
        const confirmPassword = confirmPassword.value;

        const request = {
            method: "PUT",
        }
        const url = `${USER_API_BASE_URL}/${me.id}/updatePassword?oldPassword=${oldPassword}&newPassword=${newPassword}`

        fetch(url, request)
            .then(function(response){
        CreateView("/");
        });
    });
}

function doTogglePasswordHandler() {
    const button = document.querySelector("#toggleShowPassword");
    button.addEventListener("click", function (event) {
    //    grab a reference to confirm password field
        const oldPassword = document.querySelector('#oldpassword');
        const newPassword = document.querySelector('#newpassword');
        const confirmPassword = document.querySelector('#confirmpassword');
        if(confirmPassword.getAttribute(("type")) === "password") {
            confirmPassword.setAttribute("type", "text");
            oldPassword.setAttribute("type", "text");
            newPassword.setAttribute("type", "text");
            }else {
                confirmPassword.setAttribute("type", "password");
                oldPassword.setAttribute("type", "password");
                newPassword.setAttribute("type", "password");
            }
    });
}