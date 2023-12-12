import React from 'react';
import { FormEvent, useState } from 'react';
import api from '../../../api/axiosConfig.js';
import { Grave, GraveType } from '../grave/Grave.tsx';


export const GraveByIdForm = () => {
    const [id, setId] = useState('');
    const [grave, setGrave] = useState<GraveType>();
    const [isResultVisible, setResultVisible] = useState(false);

    const getGrave = async (id : string) => {
        try {
            setGrave((await api.get(`/graves/${id}`)).data);
        } catch (error) {
            setResultVisible(false);
            console.log(error);
        }
    };

    const handleInput = (e: React.FormEvent<HTMLInputElement>) => {
        setId(e.currentTarget.value);
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setResultVisible(true);
        getGrave(id);
    };

  return (
    <div>
        <h4 className='title'>FIND GRAVE BY ID</h4>
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
        <Grave {...grave!}/>}
    </div>
  )
}
