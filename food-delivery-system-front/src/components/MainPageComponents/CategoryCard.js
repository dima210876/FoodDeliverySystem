import './CategoryCard.css'

function CategoryCard(props){
    return(
        <a className="text" href="">
        <div className="category-card">
            <img src ={props.imageUrl} alt="" width={100} height={100} />
                <span >{props.title}</span>
        </div>
        </a>
    );
}

export default CategoryCard