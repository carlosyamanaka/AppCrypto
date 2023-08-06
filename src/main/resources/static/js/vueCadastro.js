const { createApp } = Vue;

const userUrl = "http://localhost:8080";

async function registerUser(email, pass) {
  try {
    const endpoint = '/auth/register';
    const url = userUrl + endpoint;
    console.log(url)
    const requestBody = {
        email: email,
        pass: pass,
    };
    console.log(requestBody)
    const response = await axios.post(url, requestBody);
    console.log(requestBody)
    console.log(response.data);
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
        repeatPassword: '',
      };
    },
    methods: {
      async onSubmit() {
        try {
          if (this.password !== this.repeatPassword) {
            console.log("The password is wrong!!!!!!!!!!");
            return;
          }
  
          const response = await this.registerUser(this.email, this.password);
        } catch (error) {
          console.error('Error: ', error);
        }
      },
      registerUser: registerUser,
    },
  };
  
  

createApp(mainContainer).mount('#cadastro');