import React from 'react'
const axios = require('axios');
import GameMode from './GameProperties/GameMode';
import GameDifficulty from './GameProperties/GameDifficulty';
import NewWorld from "./GameProperties/NewWorld";

const MinecraftProperties=()=>{
	//const[jsonObject,setJsonObject] = useState('');
	const apiPOST= (url,jsonObject)=>{
        // Make a request for a user with a given ID
        axios.defaults.withCredentials = true;
        const promise = axios.post(url,jsonObject,{
			headers:{'Content-Type':'application/json'}
		});
        //console.log(promise);

        // using .then, create a new promise which extracts the data
        const dataPromise = promise.then((response) => response.data)

        // return it
        return dataPromise
    }
    
    return (
        <div>
            <h1>Server Properties</h1>
            <GameMode/>
            <GameDifficulty/>
            <NewWorld/>
        </div>

    )
}

export default MinecraftProperties;