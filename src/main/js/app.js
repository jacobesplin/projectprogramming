'use strict';


const React = require('react');
const ReactDOM = require('react-dom');
import "regenerator-runtime/runtime";
//import "./app.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Minecraft from "./minecraft/Minecraft";





const App = () =>{
    return(
        <div>
            <Router>
                <Switch>
                    <Route path="/minecraft/server" exact component={() => <Minecraft />} />               
				</Switch>
            </Router>
        </div>
    )
}


ReactDOM.render(
    <App/>,document.getElementById('react')
)

