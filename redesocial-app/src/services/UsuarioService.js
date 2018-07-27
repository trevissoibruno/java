import CONFIG from "../config";
import axios from "axios";
import LoginService from "./LoginService";
export default class UsuarioService {

    static getUsuarioLogado() {
        return axios.get(`${CONFIG.API_URL_BASE}/public/usuario/buscarLogado`, {
            headers: {
              Authorization: `${LoginService.getLoggedUser()}`,
              "Content-Type": "application/json"
            }
          });
  }

}