import React from 'react';
import { FormEvent, useState } from 'react';
import api from '../../../api/axiosConfig.js'

export const RemoveGrave = () => {
    const [id, setId] = useState('');
    const [isMessageVisible, setIsMessageVisible] = useState(false);

    const removeGrave = async (id : string) => {
        try {
            await api.delete(`/graves/${id}`);
        } catch (error) {
            setIsMessageVisible(false);
            console.log(error);
        }
    }

    const handleInput = (e: React.FormEvent<HTMLInputElement>) => {
        setId(e.currentTarget.value);
    };

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setIsMessageVisible(true);
        removeGrave(id);
    };

    return (
        <>
        <h4 className='title'>REMOVE GRAVE BY ID</h4>
        <form className='body' onSubmit={handleSubmit}>
            <label htmlFor='id'>
                <input
                    id='id'
                    value={id}
                    onChange={handleInput}
                    placeholder='ID'/>
            </label>
            <button className='button' type='submit'>Remove</button>
        </form>
        {isMessageVisible &&
        <p>Grave of id = {id} has been deleted.</p>}
        </>
    )
}
