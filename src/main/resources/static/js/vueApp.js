const { createApp } = Vue;

const baseUrl = "https://api.coincap.io/v2/assets";
const userUrl = "http://localhost:8080";

async function getUsuarioAtual() {
  try {
    const endpoint = "/user/profile";
    const url = userUrl + endpoint;
    const response = await axios.get(url);
    console.log(response.data);
    return response.data;
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
    this.getAuthToken();
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
      const maximumFractionDigits = value < 10 ? 2 : 0;
      return Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
        maximumFractionDigits,
        minimumFractionDigits: 0,
      }).format(value);
    },
    async showAllCoins() {
      try {
        const response = await axios.get(baseUrl);
        const coinsData = response.data.data;
        this.coins = Object.values(coinsData);
      } catch (error) {
        console.error("Error fetching coins:", error);
      }
    },
    async addCoinToList(coinId) {
      try {
        const user = await this.getUsuarioAtual();
        const endpoint = `/user/${user}`;
        const urlfinal = userUrl + endpoint;
        await axios.patch(urlfinal, coinId);
        toastr.success("Added!!");
      } catch (error) {
        toastr.error("Error");
      }
    },    
    getUsuarioAtual,
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
  },
};

createApp(mainContainer).mount("#app");
