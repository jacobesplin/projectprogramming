import React,{useState} from 'react';
import "./LoginForm.css"

const LoginForm=()=>{
    const [enteredUsername, setEnteredUsername] = useState('');
    const [enteredPassword, setEnteredPassword] = useState('');
    
    const usernameHandler = (event) => {
        setEnteredUsername(event.target.value);
      };
    
    const passwordHandler = (event) => {
        setEnteredPassword(event.target.value);
      }
    const submitHandler = (event) => {
        event.preventDefault();
    
        const loginData = {
          username: enteredUsername,
          password: enteredPassword,
        };
    
        //props.onSaveExpenseData(loginData);
        setEnteredUsername('');
        setEnteredPassword('');
        console.log("login form used");

      };
      const register =()=>{
          console.log("Need to register");
      }
    return(
        <form>
            <div className="form__control">
                <label>Username:</label><br></br>
                <input
                    type='text'
                    onChange={usernameHandler}
                />
            </div>
            <div className="form__control">
                <label>Password</label><br></br>
                <input
                    type='password'
                    onChange={passwordHandler}
                />
            </div>
            <div>
                <button type='submit'>Login</button>
                <button type='Register' onClick={register}>Register</button>
            </div>
        </form>
        

    );
};

export default LoginForm;