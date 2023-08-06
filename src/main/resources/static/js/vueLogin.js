const { createApp } = Vue;

const userUrl = "http://localhost:8080";

async function loginUser(email, pass) {
  try {
    const endpoint = '/auth/login';
    const url = userUrl + endpoint;
    const requestBody = {
        email: email,
        pass: pass,
    };
    const response = await axios.post(url, requestBody);
    return response.data;
    } catch (error) {
        console.error('Error: ', error);
        throw error;
    }
}

const mainContainer = {
  
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    async onSubmit() {
      try {
        const response = await this.loginUser(this.email, this.password);
      } catch (error) {
        console.error('Error: ', error);
        return;
      }
    },
    registerUser: registerUser,
  },
};
  
  

createApp(mainContainer).mount('#login');