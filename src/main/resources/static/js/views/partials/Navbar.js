export default function Navbar(props) {
    return `
<!--<nav class="nav nav-pills nav-justified">-->
<!--  <a class="nav-item data-link active" href="/">Home</a>-->
<!--  <a class="nav-item data-link" href="/posts">Posts</a>-->
<!--  <a class="nav-item data-link" href="/about">About</a>-->
<!--  <a class="nav-item data-link" href="/login">Login</a>-->
<!--  <a class="nav-item data-link" href="/register">Register</a>-->
<!--  <a class="nav-item data-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>-->
<!--</nav>-->
        <nav>
            <a href="/" data-link>Home</a>
            <a href="/posts" data-link>Posts</a>
            <a href="/about" data-link>About</a>
            <a href="/login" data-link>Login</a>
            <a href="/register" data-link>Register</a>
        </nav>
    `;
}