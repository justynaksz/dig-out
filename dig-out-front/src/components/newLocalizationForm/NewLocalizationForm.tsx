import React, { ChangeEvent, FormEvent, useState } from 'react';
import api from '../../api/axiosConfig.js';
import { LocalizationType } from '../localization/Localization.tsx';

export const NewLocalizationForm = () => { 
    const [localization, setLocalization] = useState<LocalizationType>({} as LocalizationType);

    const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
    setLocalization((prev) => ({
        ...prev,
        [name]: value,
    }));
  };

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    addLocalization({
        ...localization,
        id: '0'
    });
  };

    const addLocalization = async (newLocalization: LocalizationType) => {
        try {  
          const createdLocalization = newLocalization
          await api.post("/localizations", createdLocalization)
        } catch (error) {
          console.log(error)
        };
      };

  return (
    <>
        <h4 className='title'> CREATE NEW LOCALIZATION </h4>
        <form className='body' onSubmit={handleSubmit}>
            <label htmlFor='cemetery'>
                <input
                    type='text'
                    id='cemetery'
                    name='cemetery'
                    value={localization.cemetery || ''}
                    onChange={handleInput}
                    placeholder='Cemetery'
                />
            </label>
            <label htmlFor='quarter'>
                <input
                    type='text'
                    id='quarter'
                    name='quarter'
                    value={localization.quarter || ''}
                    onChange={handleInput}
                    placeholder='Quarter'
                />
            </label>
            <label htmlFor='localizationRow'>
                <input
                    type='text'
                    id='localizationRow'
                    name='localizationRow'
                    value={localization.localizationRow || ''}
                    onChange={handleInput}
                    placeholder='Row'
                />
            </label>
            <label htmlFor='localizationColumn'>
                <input
                    type='text'
                    id='localizationColumn'
                    name='localizationColumn'
                    value={localization.localizationColumn || ''}
                    onChange={handleInput}
                    placeholder='Column'
                />
            </label>
            <button className='button' type='submit'>Add Localization</button>
        </form>
    </>
  )
}
