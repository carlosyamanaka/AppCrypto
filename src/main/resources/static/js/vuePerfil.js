const { createApp } = Vue;

const baseUrl = "https://api.coincap.io/v2/assets";
const userUrl = "http://localhost:8080";

async function getUsuarioAtual() {
  try {
    const endpoint = "/user/profile";
    const url = userUrl + endpoint;
    const response = await axios.get(url);
    return response.data;
  } catch (error) {
    throw error;
  }
}

async function getUserCoin(email) {
  try {
    const response = await axios.get(`${userUrl}/user/usercoins`, {
      params: {
        email: email
      }
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

const mainContainer = {
  data() {
    return {
      email: '',
      coins: [],
      userCoins: [],
      pWord: '',
    };
  },

  async mounted() {
    this.getAuthToken();
    this.email = await getUsuarioAtual();
    this.coins = await getUserCoin(this.email);
    this.getUserCoinDetailed();
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
      const maximumFractionDigits = value < 10 ? 2 : 0;
      return Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
        maximumFractionDigits,
        minimumFractionDigits: 0,
      }).format(value);
    },

    async getUserCoinDetailed() {
      for (const coin of this.coins) {
        const endpoint = `/${coin.id}`;
        const url = baseUrl + endpoint;
        try {
          const response = await axios.get(url);
          this.userCoins.push(response.data.data);
        } catch (error) {
          console.error(error);
        }
      }
    },

    getAuthToken() {
      const cookies = document.cookie.split("; ");
      for (const cookie of cookies) {
        const [name, value] = cookie.split("=");
        if (name === "auth_token") {
          if (value) {
            axios.defaults.headers.common["Authorization"] = `Bearer ${value}`;
          } else {
            delete axios.defaults.headers.common["Authorization"];
          }
          return value;
        }
      }
      return null;
    },

    async removeCoinFromList(coinId) {
      try {
        const user = await getUsuarioAtual();
        const endpoint = `/user/removeMoeda`;
        const requestBody = {
          email: user,
          coin: coinId,
        };
        const urlfinal = userUrl + endpoint;
        await axios.patch(urlfinal, requestBody);    
        const index = this.userCoins.findIndex(coin => coin.id === coinId);
        if (index !== -1) {
          this.userCoins.splice(index, 1);
        }
        await this.$nextTick();
        toastr.success("Removed!!");
      } catch (error) {
        console.log(error);
        toastr.error("Error");
      }
    },

      async updateUserPassword() {
        try {
          const user = await this.getUsuarioAtual();
          const endpoint = `/user/password`;
          const requestBody = {
            email: user,
            pass: this.pWord,
          };
          const urlfinal = userUrl + endpoint;
          console.log(this.pWord);
          await axios.patch(urlfinal, requestBody);
          toastr.success("Updated!!!!!!!!");
        } catch (error) {
          toastr.error("Error");
        }
      },  

    }
};



createApp(mainContainer).mount("#perfil");
