import React from 'react'
import { FormEvent, useState } from 'react'
import api from '../../../api/axiosConfig.js'
import { Deceased, DeceasedType } from '../deceased/Deceased.tsx'

export const DeceasedByIdForm = () => {
    const [id, setId] = useState('')
    const [deceased, setDeceased] = useState<DeceasedType>()
    const [isResultVisible, setResultVisible] = useState(false)

const getDeceased = async (id : string) => {
    try {
        setDeceased((await api.get(`/deceased/${id}`)).data)
    } catch (error) {
        setResultVisible(false) 
        console.log(error)
    }
}

const handleInput = (e: React.FormEvent<HTMLInputElement>) => {
    setId(e.currentTarget.value)
}

const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setResultVisible(true)
    getDeceased(id)
}

return (
    <div>
        <h4 className='title'>FIND DECEASED BY ID</h4>
        <form className='body' onSubmit={handleSubmit}>
            <label htmlFor='id'>
                <input
                id='id'
                value={id}
                onChange={handleInput}
                placeholder='ID'/>
            </label>
            <button className='button' type='submit'>Find</button>
        </form>

        {isResultVisible &&
        <Deceased {...deceased!}/>}
    </div>
)
}
