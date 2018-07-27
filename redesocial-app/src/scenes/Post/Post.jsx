import React from 'react'
import {
    Card,
    CardBody,
    CardTitle,
    CardText,
    CardImg
  } from "reactstrap";
  import Button from '../../components/gereric/Button/Button'
import './Post.css'

export default class Post extends React.Component {

  
    render(){
        return(
          <div className="post">
            <Card>
              <CardImg
                top
                width="100%"
                src={this.props.image}
                alt="Card image cap"
              />
              <CardBody>
                <CardTitle>{this.props.title}</CardTitle>
                <CardText>
                {this.props.usuario}
                </CardText>
                <CardText>
                {this.props.text}
                </CardText>
                <CardText>
                  <small className="text-muted">{this.props.data}</small>
                </CardText>
                <div>
                <Button isOutline={true} classButton="warning" type="button" onClick={this.props.onClick} text="Comentar"/>
                </div>
               
              </CardBody>
              
              {this.props.comentario}  
            </Card>

            </div>

        )
    }



}