import './categoryCard.css'
import {Link, useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";

const CHOOSE_CATEGORY = 'CHOOSE_CATEGORY';

function CategoryCard(props){

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const openProductPage = () => {
        dispatch({type: CHOOSE_CATEGORY, payload:
            props.title
        })
        navigate("/products");
    }

    return(
        <div className="category-card" onClick={openProductPage}>
            <img src={props.imageUrl} alt="" width={80} height={80} />
            <div >{props.title}</div>
        </div>

    );
}

export default CategoryCard