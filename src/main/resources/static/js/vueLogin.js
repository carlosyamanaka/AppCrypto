const { createApp } = Vue;

const userUrl = "http://localhost:8080";

async function getUsuarioAtual() {
  try {
    const endpoint = '/user/profile';
    const url = userUrl + endpoint;
    await axios.get(url).then(response => {
      console.log(response.data);
    })
    } catch (error) {
       throw error;
    }
}

const setAuthToken = (token) => {
  if (token) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
    delete axios.defaults.headers.common['Authorization'];
  }
};

async function loginUser(email, pass) {
  try {
    const endpoint = '/auth/login';
    const url = userUrl + endpoint;
    const requestBody = {
      email: email,
      pass: pass,
    };
    const response = await axios.post(url, requestBody);
    const token = response.data.token;
    document.cookie = `auth_token=${token}; max-age=${30 * 24 * 60 * 60}; path=/; secure=true;`;
    setAuthToken(token);
    window.location.href = 'index.html';
    return response.data;
  } catch (error) {
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
        await this.loginUser(this.email, this.password);
      } catch (error) {
        toastr.error('Invalid credentials. Please check your email and password.', 'Authentication Error');
        return;
      }
    },
    getUsuarioAtual: getUsuarioAtual,
    loginUser: loginUser,
  },
};
  
  

createApp(mainContainer).mount('#login');