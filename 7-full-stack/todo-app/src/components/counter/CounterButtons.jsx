const CounterButtons= ({by = 1, increment, decrement})=>{
    
    return (
        <div className="counter-buttons">
            <button type="button" className="counter-increment" onClick={()=> increment(by)}> +{by} </button>
            <button type="button" className="counter-decrement" onClick={()=> decrement(by)}> -{by} </button>
        </div>
    );
}

export default CounterButtons;