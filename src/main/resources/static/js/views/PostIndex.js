import CreateView from "../createView.js";

export default function PostIndex(props) {
    const postsHTML= generatePostsHTML(props.posts);
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <h3>Lists of posts</h3>
            <div>
                ${postsHTML}   
            </div>
            <br>
                <h3>Add a post</h3>
                <form action ="">
                    <label for="title">Title</label><br>
                    <input id="title" name="title" type="text" placeholder="Enter title" required>
                    <br>
                    <br>
                    <label for="content">Content</label><br>
                    <textarea id="content" name="content" rows="10" cols="50" placeholder="Enter content" required></textarea>
                    <button id="addPost" name="addPost">Add Post</button>
                </form>
        </main>
    `;
}
function generatePostsHTML(posts){
    let postsHTML = `
                    <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Content</th>
                    </tr>
                    </thead>
                    </table>
                    <tbody>`;
    for (let i = 0; i < posts.length; i++) {
        const post = posts[i];
        postsHTML += `<tr>    
                           <td>${post.title}</td>
                           <td>${posts.content}</td>
                           // <td>${posts.categories}</td>
                           // <td>${posts.authorName}</td>
                           <td><button data-id=${posts.id} class="editPost">Edit</button></td>
                           <td><button data-id="${posts.id}"class="deletePost">Delete</button></td>
                       </tr>`;}
    postsHTML += `</tbody></table>`;
        return postsHTML;
    }
export function postSetup() {
    addPostHandler();
    editPostHandlers();
    deletePostHandlers();
}
    function editPostHandlers() {
        const ediButtons = document.querySelectorAll(".editPost");
        for (let i = 0; i< editButtons.length; i++) {
            editButtons[i].addEventListener("click", function (event) {
                console.log("post to be edited");
            });
        }
}

function deletePostHandlers() {

}
function addPostHandler() {
    const addButton = document.querySelector("#addPost");
    addButton.addEventListener("click", function (event) {
        // console.log("clicked");
        const titleField = document.querySelector("#title");
        const contentField = document.querySelector("#content");
        if((titleField.value === "") ||(contentField.value === "")){
            console.log("needs more data");
        }

        let newPost = {
            id: null,
            title: titleField.value,
            content: contentField.value
        }

        //TODO: Create a request object with method, headers, and body properties.
        // console.log(newPost);
        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newPost)
        }
        fetch("http://localhost:8080/api/posts", request)
            .then(response => {
                console.log(response.status);
                CreateView("/posts");
            })
    });
}
// function editPostHandlers() {
//     const editButtons = document.querySelectorAll(".editPost");
//     const titleField =  document.querySelector("#title");
//     const contentField = document.querySelector("#content");
//     for (let i = 0; i < editButtons.length; i++) {
//         editButtons[i].addEventListener("click", function(event) {
//             console.log(editButtons[i].getAttribute("data-id") + "will be edited");
//             if((titleField.value === "") || (contentField.value === "")) {
//                 console.log("needs more data");
//             }
//             else{
//                 let editPost = {
//                     title: titleField.value,
//                     content: contentField.value,
//                 }
//                 let request = {
//                     method: "PUT",
//                     headers: {"Content-Type": "application/json"},
//                     body: JSON.stringify(editPost)
//                 }
//                 let url = `http://localhost:8080/api/posts/${editButtons[i].getAttribute("data-id")}`;
//                 fetch(url, request).then(response => response.json());
//                 location.reload();
//             }});
//     }
// }
// function deletePostHandlers() {
//     const deleteButtons = document.querySelectorAll(".deletePost");
//     for (let i = 0; i < deleteButtons.length; i++) {
//         deleteButtons[i].addEventListener("click", function (event) {
//             console.log(deleteButtons[i].getAttribute("data-id") + "will be deleted");
//             let request = {
//                 method: "DELETE",
//                 headers: {"Content-Type": "application/json"},
//             }
//             let url = `http://localhost:8080/api/posts/${deleteButtons[i].getAttribute("data-id")}`
//             fetch(url, request).then(response => response.json());
//             location.reload();
//
//         });
//     }
// }
// export function postSetup() {
//     addPostHandler();
//     editPostHandlers();
//     deletePostHandlers();
//
// }