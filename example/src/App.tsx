import * as React from 'react';

import { Button, StyleSheet, Text, View } from 'react-native';
import {
  getPermission,
  useSEA,
} from 'react-native-schedule-exact-alarm-permission';

export default function App() {
  const SEAstatus = useSEA();

  return (
    <View style={styles.container}>
      <Text>Result: {`${SEAstatus}`}</Text>
      <Button
        title="Get SEA permission"
        onPress={() => {
          getPermission();
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
