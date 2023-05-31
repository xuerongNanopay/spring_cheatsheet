import {  Button, Space  } from 'antd';
import { useState } from 'react'

const Logout = _ => {
  const [ disable, setDisable ] = useState(false)
  const logout = ( e ) => {
    setDisable(true)
    console.log("On logout")
  }
  return (
    <>
      <h1>Login Out</h1>
      <Space>
        <Button type='primary' disabled={disable} shape='round' onClick={logout}>Logout</Button>
      </Space>
    </>
  )
}
export default Logout