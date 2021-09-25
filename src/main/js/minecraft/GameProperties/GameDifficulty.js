import React,{useState} from 'react'
const axios = require('axios');


const GameDifficulty=()=>{
	const[gameDifficulty,setGameDifficulty] = useState('');
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
    const onChangeSetGameDifficulty=(event)=>{
        setGameDifficulty(event.target.value);
        document.getElementById("gameDifficultySelection").innerHTML= event.target.value;
    }
    const sendData = async(event) =>{
        event.preventDefault();
        if(gameDifficulty != ""){
        
            var jsonObject= {
                data:"gameDifficulty",
                property:gameDifficulty
            }
            
            const a = apiPOST('/ReactSpringBoot/api/v12/data/set/minecraft',jsonObject).then(data => {
                //console.log(data);
                return data;
            })
            .catch(err => console.log(err))
            var data = await a;
            document.getElementById("gameDifficultySelection").innerHTML= data['data'];
        }else{
            document.getElementById("gameDifficultySelection").innerHTML="Please make a selection";
        }

    }
    return (
        <div>
            <h4>Game Difficulty</h4>
            <div><p id = "gameDifficultySelection"></p></div>
            <select name="gameDifficulty" id="gameDifficulty" onChange={onChangeSetGameDifficulty}>
                <option value="peaceful">peaceful</option>
                <option value="easy">easy</option>
                <option value="normal">normal</option>
                <option value="hard">hard</option>
            </select>
            
            <button onClick={sendData}>Submit</button>
        </div>

    )
}

export default GameDifficulty;