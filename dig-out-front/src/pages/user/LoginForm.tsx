import React, { ChangeEvent } from 'react'
import api from '../../api/axiosConfig.js';
import { FormEvent, useState } from 'react'
import { SignInRequestType } from '../../components/users/signin/SignInRequest';
import { setAuthToken } from '../../helpers/setAuthToken.tsx';

export const LoginForm = () => {
    const [request, setRequest] = useState<SignInRequestType>({} as SignInRequestType);

    const signIn = async (signInRequest: SignInRequestType) => {
        try {
            await api.post(`/user/signin`, signInRequest).then(response => {
                const token = response.data.token;
                localStorage.setItem("token", token);
                localStorage.setItem("userId", response.data.userId)
                setAuthToken(token)
                redirect(token)
            })
        } catch(error) {
            console.log(error)
        }
    }

    const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setRequest((prev) => ({
            ...prev,
            [name]: value
        }))

    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        signIn(request)
    }

    const redirect = (token: string) => {
        if (token) {
            window.location.href = '/profile'
        } else {
            window.location.href = '/notloggedin'
        }
        
    }

    return (
        <>
            <h4 className='title'> LOGIN </h4>
            <form className='body' onSubmit={handleSubmit}>
                <div>
                   <label htmlFor='nickanme'>
                        <input
                        className='input'
                        type='text'
                        id='nickname'
                        name='nickname'
                        value={request?.nickname || ''}
                        onChange={handleInput}
                        placeholder='Nickname'
                        />
                    </label> 
                </div>
                <div>
                    <label htmlFor='password'>
                        <input
                        className='input'
                        type='password'
                        id='password'
                        name='password'
                        value={request?.password || ''}
                        onChange={handleInput}
                        placeholder='Password'
                        />
                    </label> 
                </div>
                <button className='button' type='submit'>Login</button>
            </form>
            </>
    )
}
