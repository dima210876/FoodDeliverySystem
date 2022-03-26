import './sortBar.css'

function SortNavbar(){
    return(
        <div className="sort-bar">
            <div><span className="sort-text">Sort by:</span></div>
            <div><button className="button-sort">price</button></div>
            <div><button className="button-sort">popularity</button></div>
        </div>
    );
}

export default SortNavbar