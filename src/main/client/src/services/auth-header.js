export default function authHeader() {
  const data = JSON.parse(localStorage.getItem('data'));

  if (data && data.token) {
    return { Authorization: `Bearer ${data.token}` };
  } return {};
}
