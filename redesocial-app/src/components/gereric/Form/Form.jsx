import React from 'react'
import Input from '../Input/Input'
import TextArea from '../TextArea/TextArea'
export default class Form extends React.Component {

    render(){
        return(
            <div>
            <Input placeholder="Digite titulo da resenha" name="title" type="text" 
            value={this.props.title} 
            handleChange={this.props.handleChange} label="Titulo" />

            <Input placeholder="Digite url da imagem" name="image" type="text" 
            value={this.props.image} 
            handleChange={this.props.handleChange} label="Imagem" />
            
            <TextArea  placeholder="Digite aqui sua resenha" name="text" type="text" 
            value={this.props.text} 
            handleChange={this.props.handleChange} />
            </div>
        );
    }


}