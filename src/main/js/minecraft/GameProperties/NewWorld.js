import React from 'react'
const axios = require('axios');


const deleteWorld=()=>{
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
    const sendData = async(event) =>{
        event.preventDefault();
        if(gameMode != ""){
        
            var jsonObject= {
                data:"newWorld",
                property:"newWorld"
            }
            
            const a = apiPOST('/ReactSpringBoot/api/v12/data/set/minecraft',jsonObject).then(data => {
                //console.log(data);
                return data;
            })
            .catch(err => console.log(err))
            var data = await a;
            document.getElementById("newWorld").innerHTML= data['data'];
        }else{
            document.getElementById("newWorld").innerHTML="Please make a selection";
        }

    }
    return (
        <div>
            <h4>New World</h4>
            <div><p id = "newWorld"></p></div>
            <button onClick={sendData}>Submit</button>
        </div>

    )
}

export default deleteWorld;