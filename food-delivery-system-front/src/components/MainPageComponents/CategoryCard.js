import './CategoryCard.css'

function CategoryCard(props){
    return(
        <a className="text" href="https://www.youtube.com/">
        <div className="category-card">
            <img src ={props.imageUrl} alt="" width={100} height={100} />
                <span >{props.title}</span>
        </div>
        </a>
    );
}

export default CategoryCard