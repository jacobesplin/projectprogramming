import React,{useState} from 'react'
const axios = require('axios');


const GameMode=()=>{
	const[gameMode,setGameMode] = useState('');
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
    const onChangeSetGameMode=(event)=>{
        setGameMode(event.target.value);
        document.getElementById("gameModeSelection").innerHTML= event.target.value;
    }
    const sendData = async(event) =>{
        event.preventDefault();
        if(gameMode != ""){
        
            var jsonObject= {
                data:"gameMode",
                property:gameMode
            }
            
            const a = apiPOST('/ReactSpringBoot/api/v12/data/set/minecraft',jsonObject).then(data => {
                //console.log(data);
                return data;
            })
            .catch(err => console.log(err))
            var data = await a;
            document.getElementById("gameModeSelection").innerHTML= data['data'];
        }else{
            document.getElementById("gameModeSelection").innerHTML="Please make a selection";
        }

    }
    return (
        <div>
            <h4>Game Mode</h4>
            <div id = "gameModeSelection"></div>
            <select name="gameMode" id="gameMode" onChange={onChangeSetGameMode}>
                <option value="survival">survival</option>
                <option value="creative">creative</option>
                <option value="adventure">adventure</option>
                <option value="spectator">spectator</option>
            </select>
            
            <button onClick={sendData}>Submit</button>
        </div>

    )
}

export default GameMode;