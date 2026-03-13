const  BASE_URL = 'http://localhost:8080'

const get = async (endpoint) => {
    const response = await fetch(`${BASE_URL}${endpoint}`)
    const data = await response.json()
    return data
}

const post = async (endpoint,body) => {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(body)
    }
    ) 
    const data = await response.json()
    return data
}

export { get, post}