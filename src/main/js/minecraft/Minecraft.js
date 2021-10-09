import React,{useEffect} from 'react'
const axios = require('axios');
import MinecraftProperties from './MinecraftProperties';
import './Minecraft.css';
const Minecraft=()=>{
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
    const turnServerOn = async (event)=>{
        event.preventDefault();
		var jsonObject={
			server:"on"
		}
		const a = apiPOST('/api/v12/data/get/minecraft',jsonObject).then(data => {
            //console.log(data);
            return data;
        })
        .catch(err => console.log(err))
        var data = await a;
		document.getElementById("serverMinecraftStatus").innerHTML= data['data'];
		
	}
	const turnServerOff = async (event)=>{
        event.preventDefault();
		var jsonObject={
			server:"off"
		}
		const a = apiPOST('/api/v12/data/get/minecraft',jsonObject).then(data => {
            //console.log(data);
            return data;
        })
        .catch(err => console.log(err))
        var data = await a;
		document.getElementById("serverMinecraftStatus").innerHTML= data['data'];
		
	}
	const checkServerStatus = async () =>{
		var jsonObject= {
			server:"status"
		}
		
		const a = apiPOST('/api/v12/data/get/minecraft',jsonObject).then(data => {
            //console.log(data);
            return data;
        })
        .catch(err => console.log(err))
        var data = await a;
		document.getElementById("serverMinecraftStatus").innerHTML= data['data'] ;
		
	}
	//checkServerStatus();
	useEffect(() => {
		checkServerStatus();
	  }, []);
    return (
        <div>
			<div><p id="serverMinecraftStatus"></p></div>
            <button onClick={turnServerOn}>Turn server on</button>
			<button onClick={turnServerOff}>Turn server off</button>
			<button onClick={checkServerStatus}>Check Server</button>
			<br></br>
			<br></br>
			<br></br>
			<MinecraftProperties/>
        </div>

    )
}

export default Minecraft;   