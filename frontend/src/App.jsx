import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

const LoginPage = () => <div>"Login Page"</div>
const RegisterPage = () => <div>"Register Page"</div>
const DashboardPage = () => <div>"Dashboard Page"</div>
const EditorPage = () => <div>"Editor Page"</div>

const App = () => {
  
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<LoginPage />}/>
      <Route path="/register" element={<RegisterPage />}/>
      <Route path="/dashboard" element={<DashboardPage />}/>
      <Route path="/editor/:id" element={<EditorPage />}/>
      <Route path="/" element={<Navigate to="/login" />}/>
    </Routes>
    </BrowserRouter>
  )
}

export default App 

