import React,{useState,useEffect} from "react";
import { useNavigate } from 'react-router-dom';
import BeneficiaryService from "../service/BeneficiaryService";

function Beneficiaries(props) {

    const history = useNavigate();

    const [beneficiaries, setBeneficiaries] = useState([]);
    const [message, setMessage] = useState('');

  
 
    useEffect(() => {
        fetchBeneficiaries();
    }, []);

    const fetchBeneficiaries = () => {
        BeneficiaryService.getBeneficiaries(1).then((response) => {
            setBeneficiaries(response.data);
        });
    console.log(beneficiaries);
    };


    // const deleteProduct = (id) => {
    //     ProductService.deleteProduct(id).then(() => {
    //        // setProducts(products.filter(product => product.id !== id));
    //        fetchProducts(); // Refresh products list
    //         setMessage('Product deleted successfully.'); 
    //          // Clear the message after 3 seconds
    //          setTimeout(() => {
    //             setMessage('');
    //         }, 2000);
    //     });
    // };

    // const viewBeneficiary = (id) => {
    //     history(`/viewProduct/${id}`);
    // };

    // const addBeneficiary = () => {
    //     history('/addProduct/_add');
    // };

    // const editBeneficiary = (id) => {
    //     history(`/addProduct/${id}`);
    // };
   /*
    
    We are using the map operator to loop over our products list and create the view
    */
            return(
            <div>
                <h1 className="text-warning">Beneficiaries List</h1>
                <br/>
                    {/* <div className = "row justify-content-center">
                        <button className="btn btn-info w-auto" onClick={addProduct}>Add Product</button>
                    </div> */}
                <br/>
                <div className="row justify-content-center" >
                    <table className="table table-success w-auto">
                     <thead>
                        <tr className="table-danger">
                            <th> Beneficiary Id</th>
                            <th> Beneficiary Name</th>
                            {/* <th> Beneficiary Account Number</th> */}
                            <th> Beneficiary NickName</th>
                        </tr>
                    </thead>
                    <tbody>
                            {beneficiaries.map(
                                    beneficiary => 
                                    <tr key={beneficiary.id}>
                                        <td> {beneficiary.bid} </td>
                                        <td> {beneficiary.name} </td>
                                        {/* <td> {beneficiary.} </td> */}
                                        <td> {beneficiary.nickName} </td>
                                        {/* <td>
                                        <button className="btn btn-success" onClick={() => editProduct(prod.pid)}>
                                                
                                           Update</button>
                                           &nbsp;
                                            <button className="btn btn-danger" onClick={() => deleteProduct(prod.pid)}>
                                               
                                          Delete </button>
                                          &nbsp;
                                           <button className="btn btn-secondary" onClick={() => viewProduct(prod.pid)}>
                                                
                                          View </button>
                                        </td>  */}
                                    </tr>
                                )
                            }
                    </tbody>
                    </table>
                </div>
                {message && <div className="alert alert-success">{message}</div>}
            </div>
        )
    }


export default Beneficiaries;