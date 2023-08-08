const { createApp } = Vue;

const baseUrl = "https://api.coincap.io/v2/assets";
const userUrl = "http://localhost:8080/";

async function getUsuarioAtual() {
  try {
    const endpoint = 'user/profile';
    const url = userUrl + endpoint;
    await axios.get(url).then(response => {
      console.log(response.data);
    })
    } catch (error) {
       throw error;
    }
}

const mainContainer = {
  data() {
    return {
      coins: [],
      DataUserAddedCoin: {
        name: "",
        pass: "",
        coin: "",
      },
    };
  },
  mounted() {
    this.showAllCoins();
    this.getUsuarioAtual();
  },

  methods: {
    formatDecimal(value) {
      if (value === null) {
        return "Undefined";
      } else if (value < 10) {
        return parseFloat(value).toFixed(2);
      }
      return parseFloat(value).toLocaleString(undefined, {
        maximumFractionDigits: 0,
      });
    },

    formatDolar(value) {
      if (value < 10) {
        return Intl.NumberFormat("en-US", {
          style: "currency",
          currency: "USD",
          maximumFractionDigits: 2,
        }).format(value);
      }
      return Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
        maximumFractionDigits: 0,
        minimumFractionDigits: 0,
      }).format(value);
    },

    showAllCoins() {
      this.coins = []; // Limpa o array coins, para garantir que ele esteja vazio antes de preenchê-lo novamente.
      axios
        .get(baseUrl) // Faz uma requisição GET para a API utilizando o Axios.
        .then((response) => {
          // Quando a resposta da API é recebida, executa a função de callback.
          const coinsData = response.data.data; // Extrai os dados específicos das moedas da resposta da API.
          for (const key in coinsData) {
            // Itera sobre as propriedades do objeto coinsData.
            if (coinsData.hasOwnProperty(key)) {
              // Verifica se a propriedade pertence ao objeto coinsData (evita iteração em propriedades herdadas).
              const item = coinsData[key]; // Armazena os dados da moeda em uma variável item.
              this.coins.push(item); // Adiciona os dados da moeda ao array coins.
            }
          }
        });
    },
    // Metodo para adicionar retornar e salvar a moeda escolhida pelo usuario para acompanhamento
    addCoinToList() {
      axios
        .post(userUrl, DataUserAddedCoin)
        .then(function (response) {
          toastr.sucess("Added!!");
        })
        .catch(function (error) {
          toastr.error("Error");
        });
    },

    getUsuarioAtual: getUsuarioAtual,

    registerUser() {},

    saveId(coinId){
      console.log(coinId);
    }



 },
};

createApp(mainContainer).mount("#app");
