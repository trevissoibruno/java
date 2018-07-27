import CONFIG from "../config";
import axios from "axios";

export default class RegisterService {
  static register(email, senha, nome, apelido, dataNascimento, imagem) {
    return axios.post(`${CONFIG.API_URL_BASE}/public/usuario`, {
      email,
      senha,
      nome,
      apelido,
      imagem,
      dataNascimento
    });
  }
}
