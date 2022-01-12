import React from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

type santProps = {};

const Santorini = (props: santProps) => {
	return (
		<div>
			<nav>
				<ul>
					<li>
						<Link to="/">Home</Link>
					</li>
					<li>
						<Link to="/about">About</Link>
					</li>
					<li>
						<Link to="/users">Users</Link>
					</li>
				</ul>
			</nav>
		</div>
	);
}

function Home() {
	return <h2>Home</h2>;
}

function About() {
	return <h2>About</h2>;
}

function Users() {
	return <h2>Users</h2>;
}

export default Santorini;