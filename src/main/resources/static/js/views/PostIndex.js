import CreateView from "../createView";

export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <h3>Lists of posts</h3>
            <div>
                ${props.posts.map(post => `<h3>${post.title}</h3>`).join('')}   
            </div>
                <h3>Add a post</h3>
                <form>
                    <label for="title">Password</label><br>
                    <input id=""
                </form>
        </main>
    `;
}
export function postSetup() {
    const addButton = document.querySelector("#addPost");
    addButton.addEventListener("click", function (event) {
        const titleField = document.querySelector(("#title"));
        const contentField = document.querySelector("#content");

        let newPost = {
            title: titleField.value,
            content: contentField.valueOf()
        }

        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newPost)
        }
        fetch("http://localhost:8080/api/users", request)
            .then(response => {
                console.log(response.status);
                CreateView("/");
            })
    })

}