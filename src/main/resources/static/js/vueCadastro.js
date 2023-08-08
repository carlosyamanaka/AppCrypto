const { createApp } = Vue;

const userUrl = "http://localhost:8080";

async function registerUser(email, pass) {
  try {
    const endpoint = '/auth/register';
    const url = userUrl + endpoint;
    const requestBody = {
        email: email,
        pass: pass,
    };
    const response = await axios.post(url, requestBody).then(window.location.href = 'login.html')
    return response.data;
    } catch (error) {
      toastr.error('Invalid credentials. Please check your email and password.', 'Authentication Error');
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
            toastr.error('As senhas não coincidem');
            return;
          }
  
          const response = await this.registerUser(this.email, this.password);
        } catch (error) {
          console.error('Error: ', error);
        }
        window.location.href = 'login.html';
      },
      registerUser: registerUser,
    },
  };
  
  

createApp(mainContainer).mount('#cadastro');