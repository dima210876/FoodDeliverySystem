import './productPageHeader.css'
import {useSelector} from "react-redux";

function ProductPageHeader(){

    const title = useSelector(state => state.category.title);

    return(
        <div className="header-title">
            <h1>{title}</h1>
        </div>
    );
}

export default ProductPageHeader