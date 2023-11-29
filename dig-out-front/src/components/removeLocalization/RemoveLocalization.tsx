import React from 'react';
import { FormEvent, useState } from 'react';
import api from '../../api/axiosConfig.js'

export const RemoveLocalization = () => {
    const [id, setId] = useState ('');
    const [isMessageVisible, setIsMessageVisible] = useState(false);

    const removeLocalization = async (id : string) => {
        try {
          await api.delete(`/localizations/${id}`);
        } catch (error) {
            setIsMessageVisible(false);
            console.log(error);
        }
    };   

    const handleInput = (e: React.FormEvent<HTMLInputElement>) => {
      setId(e.currentTarget.value);
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      setIsMessageVisible(true);
      removeLocalization(id);
    };

  return (
    <>
    <h4 className='title'>REMOVE LOCALIZATION BY ID</h4>
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
    <p>Localization of id = {id} has been deleted.</p>}
    </>
  )
}
