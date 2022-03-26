import './paginationBar.css'
import {useState} from "react";

function PaginationBar(){

    const [currentPage, setCurrentPage] = useState(1);

    return(
        <div className="pagination-bar">
            <div><button className="button-move-on-page">back</button></div>
            <div className='div-current-page'><span className="current-page">{currentPage} out of 10</span></div>
            <div><button className="button-move-on-page">forward</button></div>
        </div>
    );
}

export default PaginationBar