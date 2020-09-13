import React, { Component } from 'react'
import axios from 'axios'
import upload from './upload.svg'




class ImageUpload extends Component {
    constructor(props) {
        super(props)

        this.state = {
            image: [],
            img: '',
        }
        this.inputRef = React.createRef();
    }

    componentDidMount() {
        this.changeState()
        console.log(this.state.image)
    }

    changeState = () => {
        axios.get("http://localhost:8080/CCTech-Assignm/GetImage")
            .then(resp => {
                this.setState({
                    image: resp.data.result
                })
            })
            .catch(error => {
                alert("Something Wrong")
                console.log(error)
            })
    }

    uploadAction = (e) => {
        e.preventDefault();
        this.inputRef.current.click();

    }
    handleFile = (event) => {
        if (event.target.files && event.target.files[0]) {
            let reader = new FileReader();
            reader.onload = (e) => {
                this.setState({
                    img: e.target.result
                })
                let image = { image: this.state.img }
                image = JSON.stringify(image)
                this.upload(image);
            };

            reader.readAsDataURL(event.target.files[0])
        }

    }
    upload = (image) => {
        
        axios.post("http://localhost:8080/CCTech-Assignm/uploadnewimage", image)
            .then(resp => {
                alert("Image Uploaded")
                console.log(resp)
                this.changeState();
            })
            .catch(error => {
                alert("Server Side Problem")
                console.log("Not Uploaded")
            })
    }

    render() {

        return (<>
            <div style={{backgroundColor:"#ebf8ff"}} >
                <div className="hf" style={{ height: "50px", fontSize: "30px", paddingTop: "0px" }}>Gallery</div>

                <input type="file" accept="image/*" required ref={this.inputRef} onChange={this.handleFile}
                    name="img" hidden></input>
                    <br /><br />

                <button onClick={this.uploadAction} className="bttn" >
                    <img src={upload} alt="Upload" style={{ width: "25px", paddingRight: "7px" }} />
                    Upload</button>
                <br /><br />

                <div className="container-fluid mb-5 " style={{ paddingLeft: "30px", paddingTop: "30px" }}>
                    <div className="row justify-content-center">
                        <div className="col-10 mx-auto">
                            <div className="row gy-4" >
                                {
                                    this.state.image.map((image, i) => {
                                    return <div key={i} className="col-xs-12 col-sm-6 col-md-4">
                                        <figure className="f">
                                            <img src={image.imgsrc} className="card-img-top"
                                                alt="RetrivedImage" style={{ height: "200px", width: "300px" }} />
                                            <h5 className="card-title">{image.name}{"" + (i + 1)}</h5>
                                        </figure>
                                    </div>
                                })
                                }
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </>
        )
    }
}

export default ImageUpload
