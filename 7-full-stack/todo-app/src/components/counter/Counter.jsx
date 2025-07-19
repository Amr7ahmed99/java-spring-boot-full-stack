import { useState } from "react";
import './Counter.css';
import CounterButtons from "./CounterButtons";

export default function CounterComponent(){
    const[counter, setCounter]= useState(0);

    const increment=(by)=>{
        setCounter((lastVal)=> lastVal + by)
    }

    const decrement= (by)=>{
        setCounter((lastVal)=> lastVal - by);
    }

    return (
        <div className="counter-section">
            <CounterButtons by={1} increment= {increment} decrement= {decrement}/>
            <CounterButtons by={3} increment= {increment} decrement= {decrement}/>
            <CounterButtons by={5} increment= {increment} decrement= {decrement}/>
            <p className="counter-value">{counter}</p>
            <button type="reset" className="reset-button" onClick={()=> setCounter(0)}> Reset </button>
        </div>
    );
}



