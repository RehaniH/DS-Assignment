//Details.js
import axios from 'axios';
import React, { Component } from 'react';


class Details extends Component {


    constructor(props) {

        super(props);
        this.state = {
            values: [],
        }
    }

    componentDidMount() {

   this.getDetails();
        setInterval(()=> {
            this.getDetails();
        },40000)
    }

    getDetails(){
        axios.get('http://localhost:8080/v1/sensors')
            .then(response => {
                this.setState({values: response.data}, console.log(this.state.values.active))
                console.log(response)
                // console.log("Hyyyyyyyyyyyyyyyyyyyyy")
            })
            .catch(error => {
                console.log(error)
            })
    }

    stateactive = (val) => {
        if(val)
            return 'ACTIVE';
        else
            return 'DEACTIVE'
    }

    render() {
        const { values }=this.state
        return (


                    <div>


                            <table className="table table-bordered">
                            <thead>
                        <tr>

                            <th colSpan="5">SENSOR DETAILS</th>
                        </tr>
                        </thead>
                                {values.map((post)=> <tbody key={post.id}>

                        <tr>

                            <td>Floor no  {post.location.floor_no}</td>
                            <td>Room no  {post.location.room_no}</td>
                            <td>Sensor status :  {this.stateactive(post.active)}</td>
                            <td bgcolor={post.smoke_level>5 ?"#FF0000":"#00FF00"}>smoke Level  {post.smoke_level}</td>

                            <td bgcolor={post.co2_level>5 ?"#FF0000":"#00FF00"}>Co2 Level  {post.co2_level}</td>

                        </tr>


                        </tbody>)
                            }
                            </table>

                    </div>

        )

}
}

export default Details
