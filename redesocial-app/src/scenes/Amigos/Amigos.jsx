import React from "react";
import { Card, CardImg, CardText} from "reactstrap";
import './Amigos.css'
export default class Amigos extends React.Component {
  render() {
    return (
        <div className="amigos">
        <Card body outline color="primary">
        <CardImg top width="100%" src={this.props.imagem} alt="Sem imagem" />
          <CardText>{this.props.apelido} </CardText>   
        </Card>
      </div>
    );
  }
}
