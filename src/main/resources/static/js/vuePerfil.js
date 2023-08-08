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
    const response = await axios.post(url, requestBody).then(response => {
      const token = response.data.token;
      console.log(token)
      document.cookie = `authToken=${token}; max-age=${30 * 24 * 60 * 60}; path=/; secure=true;`;
      window.location.href = 'index.html';
    })
    return response.data;
    } catch (error) {
        console.error('Error: ', error);
        throw error;
    }
}

const mainContainer = {
  
  data() {
    return {
    };
  },
  methods: {

  },
};
  
  

createApp(mainContainer).mount('#perfil');